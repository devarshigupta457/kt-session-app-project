import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username = '';
  password = '';
  error = '';
  loading = false;

  constructor(private auth: AuthService, private router: Router, private route: ActivatedRoute) {}

  submit(): void {
    if (!this.username.trim() || !this.password.trim()) {
      this.error = 'Please enter username and password.';
      return;
    }
    this.error = '';
    this.loading = true;
    this.auth.login(this.username.trim(), this.password).subscribe({
      next: () => {
        this.loading = false;
        const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/courses';
        this.router.navigateByUrl(returnUrl);
      },
      error: () => {
        this.loading = false;
        this.error = 'Invalid username or password.';
      }
    });
  }
}
