import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';

export interface Course {
  id: number;
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

  constructor(private http:HttpClient) { }

 getCourses(): Observable<ApiResponse<Course[]>> {
  return this.http.get<ApiResponse<Course[]>>(
    '/kt-session/fetchAllCourses'
  );
}

   addCourse(course: Course): Observable<ApiResponse<any>> {
  return this.http.post<ApiResponse<any>>(
    '/kt-session/addCourse',
    course
  );
}

  updateCourse(course: Course): Observable<ApiResponse<any>> {
  return this.http.put<ApiResponse<any>>(
    `/kt-session/updateCourse/${course.id}`,
    course
  );
}

  deleteCourse(id: number): Observable<ApiResponse<any>> {
  return this.http.delete<ApiResponse<any>>(
    `/kt-session/deleteCourse/${id}`
  );
}
}
