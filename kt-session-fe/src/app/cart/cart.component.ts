import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CartService, CartItem } from '../services/cart.service';
import { Course } from '../services/CourseService';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  courses: Course[] = [];
  items$: Observable<CartItem[]>;
  loading = false;
  error = '';

  constructor(public cartService: CartService,
        private router: Router,
  ) {
    this.items$ = this.cartService.items$;
  }

  ngOnInit(): void {
    this.loading = true;
    this.error = '';
    this.cartService.fetchFavorites().subscribe({
      next: () => {
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        this.error = 'loading your favourites. Please try again.';
      }
    });
  }

  remove(id: string): void {
    this.cartService.removeItem(id).subscribe({
      error: () => {
        this.error = 'Could not remove this course from favourites. Please try again.';
      }
    });
  }

  openCourse(course: Course, event?: Event): void {
    event?.stopPropagation();
    this.router.navigate(['/course-view'], { queryParams: { src: course.link } });
  }
}
