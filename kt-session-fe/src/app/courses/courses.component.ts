import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

import { CartService } from '../services/cart.service';
import { CourseService } from '../services/CourseService';
import { AddFormService } from '../services/add-form.service';
import { AuthService } from '../services/auth.service';

interface Course {
  id: number;
  title: string;
  description: string;
  price: string;
  link: string;
  trending?: boolean;
}

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit, OnDestroy {

  courses: Course[] = [];
  private addedSub?: Subscription;

  constructor(
    private cartService: CartService,
    private router: Router,
    private courseService: CourseService,
    private addForm: AddFormService,
    public auth: AuthService
  ) {}

  ngOnInit(): void {
    this.loadCourses();
    this.addedSub = this.addForm.courseAdded$.subscribe(() => this.loadCourses());
  }

  ngOnDestroy(): void {
    this.addedSub?.unsubscribe();
  }

  private loadCourses(): void {
    this.courseService.getCourses().subscribe({
      next: (response: any) => {
        this.courses =
          response.data && response.data.length
            ? response.data
            : this.fallbackCourses();
      },
      error: () => {
        this.courses = this.fallbackCourses();
      }
    });
  }

  private fallbackCourses(): Course[] {
    return [
      {
        id: 1,
        title: 'Apache Kafka — Interview Notes',
        description: 'Core concepts, Spring Boot integration, and hands-on implementation.',
        price: 'Free',
        link: '/kafka.html',
        trending: true
      },
      {
        id: 2,
        title: 'Git — Interview Notes',
        description: 'Core concepts, Apache Kafka integration, and hands-on implementation.',
        price: 'Free',
        link: '/Java-interview.html'
      }
    ];
  }

  get trendingCourses(): Course[] {
    return this.courses.filter(c => c.trending);
  }

  isInCart(course: Course): boolean {
    return this.cartService.isInCart(course.id);
  }

  addToCart(course: Course, event: Event): void {
    event.stopPropagation();
    if (!this.auth.isLoggedIn) {
      this.router.navigate(['/login'], { queryParams: { returnUrl: '/courses' } });
      return;
    }
    if (!this.auth.currentUser?.userId) {
      alert('Could not find your user id. Please log in again.');
      return;
    }

    this.cartService.addItem(course).subscribe({
      error: () => alert('Could not add this course to favourites. Please try again.')
    });
  }

  openCourse(course: Course, event?: Event): void {
    event?.stopPropagation();
    this.router.navigate(['/course-view'], {
      queryParams: { src: course.link }
    });
  }

  editCourse(course: Course, event: Event): void {
    event.stopPropagation();
    this.addForm.openForEdit(course);
  }

  deleteCourse(course: Course, event: Event): void {
    event.stopPropagation();

    if (!confirm(`Delete "${course.title}"?`)) {
      return;
    }

    this.courseService.deleteCourse(course.id).subscribe({
      next: (response: any) => {
        this.addForm.notifyAdded(response.data);
      },
      error: () => {
        this.loadCourses();
      }
    });
  }
}
