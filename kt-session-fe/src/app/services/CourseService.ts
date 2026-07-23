import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, shareReplay, tap } from 'rxjs';
import { environment } from '../../environments/environment';

export interface Course {
  id: string;
  title: string;
  price: string;
  description: string;
  link: string;
  trending?: boolean;
}

export interface ApiResponse<T> {
  errorCode: string;
  errorDescription: string;
  data: T;
}

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) {}

  // Cache all courses
  private courses$?: Observable<ApiResponse<Course[]>>;

  getCourses(): Observable<ApiResponse<Course[]>> {

    if (!this.courses$) {

      this.courses$ = this.http
        .get<ApiResponse<Course[]>>(
          `${environment.apiUrl}/kt-session/fetchAllCourses`
        )
        .pipe(
          shareReplay(1)
        );
    }

    return this.courses$;
  }

  clearCache(): void {
    this.courses$ = undefined;
  }

  addCourse(course: Course): Observable<ApiResponse<any>> {

    return this.http
      .post<ApiResponse<any>>(
        `${environment.apiUrl}/kt-session/addCourse`,
        course
      )
      .pipe(
        tap(() => this.clearCache())
      );
  }

  updateCourse(course: Course): Observable<ApiResponse<any>> {

    return this.http
      .put<ApiResponse<any>>(
        `${environment.apiUrl}/kt-session/updateCourse/${course.id}`,
        course
      )
      .pipe(
        tap(() => this.clearCache())
      );
  }

  deleteCourse(id: string): Observable<ApiResponse<any>> {

    return this.http
      .delete<ApiResponse<any>>(
        `${environment.apiUrl}/kt-session/deleteCourse/${id}`
      )
      .pipe(
        tap(() => this.clearCache())
      );
  }
}