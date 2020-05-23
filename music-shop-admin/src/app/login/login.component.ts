import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import * as hash from 'hash.js';

import {AuthService} from '../services/auth.service';
import {ToolbarService} from '../services/toolbar.service';

const required = [Validators.required];
const passwordValidators = [Validators.required, Validators.minLength(6)];

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private readonly pageName = 'Sign in';

  hasError = false;
  errorText = '';

  usernameFormControl = new FormControl('', required);
  passwordFormControl = new FormControl('', passwordValidators);

  constructor(
      private authService: AuthService, private toolbarService: ToolbarService,
      private router: Router) {}

  ngOnInit() {
    this.toolbarService.setTitle(this.pageName);
  }

  onSubmit() {
    this.authService
        .login(this.usernameFormControl.value, this.passwordFormControl.value)
        .subscribe(
            result => {
              if (result.status === 'success') {
                let hashedPassword =
                    this.hashOf(this.passwordFormControl.value);
                localStorage.setItem(
                    'currentAuth',
                    btoa(`${result.adminId}:${hashedPassword}`));
                this.authService.isLoggedIn.next(true);
                this.router.navigate(['/main']);
              } else {
                this.hasError = true;
                this.errorText = 'Login or password incorrect';
              }
            },
            error => {
              if (error.status) {
                this.hasError = true;
                this.errorText = 'Login or password incorrect';
              } else {
                this.hasError = true;
                this.errorText = 'Network error';
              }
            });
  }

  private hashOf(s: string): string {
    return hash.sha256().update(s).digest('hex');
  }
}
