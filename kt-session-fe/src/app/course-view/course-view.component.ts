import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-course-view',
  templateUrl: './course-view.component.html',
  styleUrls: ['./course-view.component.css']
})
export class CourseViewComponent {

  src: SafeResourceUrl | null = null;

  constructor(route: ActivatedRoute, sanitizer: DomSanitizer) {
    const url = route.snapshot.queryParamMap.get('src');
    if (url) {
      this.src = sanitizer.bypassSecurityTrustResourceUrl(url);
    }
  }
}
