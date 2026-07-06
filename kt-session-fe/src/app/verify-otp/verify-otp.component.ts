import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.css']
})
export class VerifyOtpComponent implements OnInit {
  email = '';
  otp = '';
  loading = false;
  error = '';

  constructor(
    private auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.email = this.route.snapshot.queryParamMap.get('email') ?? '';
    if (!this.email) {
      this.router.navigate(['/signup']);
    }
  }

  submit(): void {
    const otpValue = Number(this.otp);
    if (!this.otp.trim() || !Number.isInteger(otpValue) || otpValue <= 0) {
      this.error = 'Please enter a valid OTP.';
      return;
    }

    this.error = '';
    this.loading = true;
    this.auth.verifyOtp(this.email, otpValue).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.loading = false;
        this.error = err?.error?.message || 'OTP verification failed. Please try again.';
      }
    });
  }
}
