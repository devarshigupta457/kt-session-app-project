import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, of, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthService } from './auth.service';

export interface CartItem {
  id: string;
  title: string;
  price: string;
  link: string;
}

interface FavoritePayload {
  userId: number;
  courseId: string;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private itemsSubject = new BehaviorSubject<CartItem[]>([]);
  items$ = this.itemsSubject.asObservable();

  constructor(private http: HttpClient, private auth: AuthService) {
    this.auth.user$.subscribe(user => {
      if (user?.userId) {
        this.fetchFavorites().subscribe({ error: () => undefined });
      } else {
        this.itemsSubject.next([]);
      }
    });
  }

  getItems(): CartItem[] {
    return this.itemsSubject.value;
  }

  getCount(): number {
    return this.itemsSubject.value.length;
  }

  isInCart(id: string): boolean {
    return this.itemsSubject.value.some(i => i.id === id);
  }

  addItem(item: CartItem): Observable<any> {
    const payload = this.buildPayload(item.id);
    if (!payload || this.isInCart(item.id)) {
      return of(null);
    }

    return this.http.post<any>(`${environment.apiUrl}/kt-session/addFavCourse`, payload).pipe(
      tap(() => {
        if (!this.isInCart(item.id)) {
          this.itemsSubject.next([...this.itemsSubject.value, item]);
        }
      })
    );
  }

  removeItem(id: string): Observable<any> {
    const payload = this.buildPayload(id);
    if (!payload) {
      return of(null);
    }

    return this.http.request<any>('delete', `${environment.apiUrl}/kt-session/removeFavorite`, { body: payload }).pipe(
      tap(() => {
        this.itemsSubject.next(this.itemsSubject.value.filter(i => i.id !== id));
      })
    );
  }

  fetchFavorites(): Observable<CartItem[]> {
    const userId = this.auth.currentUser?.userId;
    if (!userId) {
      this.itemsSubject.next([]);
      return of([]);
    }

    return this.http.post<any>(`${environment.apiUrl}/kt-session/fetchFavoriteCourse`, { userId }).pipe(
      tap(response => {
        this.itemsSubject.next(this.normalizeFavoriteResponse(response));
      })
    );
  }

  private buildPayload(courseId: string): FavoritePayload | null {
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
        id: String(course.id ?? course.courseId ?? favorite.courseId ?? ''),
        title: String(course.title ?? course.courseTitle ?? ''),
        price: String(course.price ?? ''),
        link: String(course.link ?? '')
      };
    }).filter((item: CartItem) => item.id.trim().length > 0 && item.title.trim().length > 0);
  }
}
