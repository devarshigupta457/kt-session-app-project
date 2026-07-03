import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface AuthUser {
  username: string;
  role: string;
  token?: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly storageKey = 'auth_user';
  private userSubject = new BehaviorSubject<AuthUser | null>(this.readStored());

  /** Emits the current user (or null when logged out). */
  user$ = this.userSubject.asObservable();
  /** Emits true when the current user is an admin. */
  isAdmin$ = this.user$.pipe(map(u => this.checkAdmin(u)));

  constructor(private http: HttpClient) {}

  /**
   * Authenticates against the backend.
   * Expected response shape: { token?: string, role?: string }.
   * Adjust the endpoint / field names to match your backend.
   */
  login(username: string, password: string): Observable<AuthUser> {
    return this.http.post<any>('/kt-session/login', { username, password }).pipe(
      map(res => {
        const user: AuthUser = {
          username,
          role: (res && res.data.role) ? res.data.role : 'USER',
          token: res ? res.data.token : undefined
        };
        this.setUser(user);
        return user;
      })
    );
  }

  /**
   * Registers a new user. The backend assigns the role (defaults to USER);
   * never trust a client-supplied role.
   * Expected response shape: { token?: string, role?: string }.
   */
  register(username: string, email: string, password: string, fullName: string): Observable<AuthUser> {
    return this.http.post<any>('/kt-session/signup', { username, email, password, fullName }).pipe(
      map(res => {
        const user: AuthUser = {
          username,
          role: (res && res.data.role) ? res.data.role : 'USER',
          token: res ? res.data.token : undefined,
        };
        this.setUser(user);
        return user;
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.storageKey);
    this.userSubject.next(null);
  }

  get currentUser(): AuthUser | null {
    return this.userSubject.value;
  }

  get isLoggedIn(): boolean {
    return !!this.userSubject.value;
  }

  get isAdmin(): boolean {
    return this.checkAdmin(this.userSubject.value);
  }

  private checkAdmin(user: AuthUser | null): boolean {
    return !!user && user.role.toUpperCase() === 'ADMIN';
  }

  private setUser(user: AuthUser): void {
    localStorage.setItem(this.storageKey, JSON.stringify(user));
    this.userSubject.next(user);
  }

  private readStored(): AuthUser | null {
    const raw = localStorage.getItem(this.storageKey);
    return raw ? JSON.parse(raw) : null;
  }
}
