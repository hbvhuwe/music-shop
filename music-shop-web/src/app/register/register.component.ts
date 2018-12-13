import {Component, OnInit} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {ToolbarService} from '../services/toolbar.service';

const required = [Validators.required];
const passwordValidators = [Validators.required, Validators.minLength(6)];

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private readonly pageName = "Sign up";

  hasError = false;
  errorText = '';

  usernameFormControl = new FormControl('', required);
  nameFormControl = new FormControl('', required);
  surnameFormControl = new FormControl('', required);
  passwordFormControl = new FormControl('', passwordValidators);

  constructor(
      private authService: AuthService, private toolbarService: ToolbarService,
      private router: Router) {}

  ngOnInit() { this.toolbarService.setTitle(this.pageName); }

  onSubmit() {
    this.authService
        .register({
          clientId: null,
          login: this.usernameFormControl.value,
          name: this.nameFormControl.value,
          surname: this.surnameFormControl.value,
          password: this.passwordFormControl.value
        })
        .subscribe(
            result => {
              if (result['status'] === 'success') {
                this.router.navigate(['/login']);
              } else {
                this.hasError = true;
                this.errorText = 'Error suring sign up, please try again later';
              }
            },
            error => {
              if (error.status) {
                this.hasError = true;
                this.errorText = 'Error suring sign up, please try again later';
              } else {
                this.hasError = true;
                this.errorText = 'Network error';
              }
            });
  }
}
