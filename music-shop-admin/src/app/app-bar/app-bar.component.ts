import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

import {AuthService} from '../services/auth.service';
import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-bar',
  templateUrl: './app-bar.component.html',
  styleUrls: ['./app-bar.component.css']
})
export class AppBarComponent implements OnInit {
  isLoggedIn = false;
  currentPageName: string;
  userId: number;
  username: string;

  constructor(
      private toolbarService: ToolbarService, private authService: AuthService,
      private router: Router) {}

  ngOnInit() {
    this.toolbarService.toolbarTitle.subscribe(title => {
      this.currentPageName = title;
    });
    this.authService.isLoggedIn.subscribe(next => {
      this.isLoggedIn = next;
      if (next) {
        this.authService.getUserInfo().subscribe(result => {
          this.username = result.login;
          this.userId = result.adminId;
          localStorage.setItem('currentUserId', result.adminId.toString());
        });
      }
    });

    if (localStorage.getItem('currentAuth')) {
      this.authService.isLoggedIn.next(true);
    }
  }

  onSignIn() {
    this.router.navigate(['/login']);
  }

  onSignOut() {
    localStorage.removeItem('currentAuth');
    this.authService.isLoggedIn.next(false);
  }
}
