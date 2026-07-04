import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';

export interface CartItem {
  id: number;
  title: string;
  price: string;
  link: string;
}

interface FavoritePayload {
  userId: number;
  courseId: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private items: CartItem[] = [];

  constructor(private http: HttpClient, private auth: AuthService) {
    this.auth.user$.subscribe(user => {
      if (user?.userId) {
        this.fetchFavorites().subscribe({ error: () => undefined });
      } else {
        this.items = [];
      }
    });
  }

  getItems(): CartItem[] {
    return this.items;
  }

  getCount(): number {
    return this.items.length;
  }

  isInCart(id: number): boolean {
    return this.items.some(i => i.id === id);
  }

  addItem(item: CartItem): Observable<any> {
    const payload = this.buildPayload(item.id);
    if (!payload || this.isInCart(item.id)) {
      return of(null);
    }

    return this.http.post<any>(`${environment.apiUrl}/kt-session/addFavCourse`, payload).pipe(
      tap(() => {
        if (!this.isInCart(item.id)) {
          this.items = [...this.items, item];
        }
      })
    );
  }

  removeItem(id: number): Observable<any> {
    const payload = this.buildPayload(id);
    if (!payload) {
      return of(null);
    }

    return this.http.request<any>('delete', `${environment.apiUrl}/kt-session/removeFavorite`, { body: payload }).pipe(
      tap(() => {
        this.items = this.items.filter(i => i.id !== id);
      })
    );
  }

  fetchFavorites(): Observable<CartItem[]> {
    const userId = this.auth.currentUser?.userId;
    if (!userId) {
      this.items = [];
      return of([]);
    }

    return this.http.post<any>(`${environment.apiUrl}/kt-session/fetchFavoriteCourse`, { userId }).pipe(
      tap(response => {
        this.items = this.normalizeFavoriteResponse(response);
      })
    );
  }

  private buildPayload(courseId: number): FavoritePayload | null {
    const userId = this.auth.currentUser?.userId;
    return userId ? { userId, courseId } : null;
  }

  private normalizeFavoriteResponse(response: any): CartItem[] {
    const favorites = Array.isArray(response?.data)
      ? response.data
      : Array.isArray(response)
        ? response
        : [];

    return favorites.map((favorite: any) => {
      const course = favorite.course || favorite;
      return {
        id: Number(course.id ?? course.courseId ?? favorite.courseId),
        title: String(course.title ?? course.courseTitle ?? ''),
        price: String(course.price ?? ''),
        link: String(course.link ?? '')
      };
    }).filter((item: CartItem) => item.id && item.title);
  }
}
