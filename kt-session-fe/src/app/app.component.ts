import { Component, OnInit, OnDestroy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { trigger, transition, style, query, animate } from '@angular/animations';
import { Subscription } from 'rxjs';
import { AddFormService } from './services/add-form.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  animations: [
    trigger('routeFade', [
      transition('* <=> *', [
        query(':enter', [style({ opacity: 0, transform: 'translateY(10px)' })], { optional: true }),
        query(
          ':enter',
          [animate('280ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))],
          { optional: true }
        ),
      ]),
    ]),
  ],
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'My_Dream_App';
  message = '';
  private addedSub?: Subscription;

  constructor(private addForm: AddFormService) {}

  ngOnInit(): void {
    this.addedSub = this.addForm.courseAdded$.subscribe((response) => this.showMessage(response));
  }

  ngOnDestroy(): void {
    this.addedSub?.unsubscribe();
  }

  private showMessage(response: any): void {
    if (typeof response === 'string' && response.trim()) {
      this.message = response;
    } else if (response && response.message) {
      this.message = response.message;
    } else {
      this.message = 'Course added successfully.';
    }
    setTimeout(() => (this.message = ''), 4000);
  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet?.activatedRouteData?.['animation'];
  }
}
