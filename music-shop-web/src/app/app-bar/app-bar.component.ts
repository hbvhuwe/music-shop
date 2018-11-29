import {Component, OnInit} from '@angular/core';

import {AuthService} from '../services/auth.service';
import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-bar',
  templateUrl: './app-bar.component.html',
  styleUrls: ['./app-bar.component.css']
})
export class AppBarComponent implements OnInit {
  isLoggedIn = true;
  currentPageName: string;

  constructor(
    private toolbarService: ToolbarService,
    private authService: AuthService) {}

  ngOnInit() {
    this.toolbarService.toolbarTitle.subscribe(title => {
      this.currentPageName = title;
    });
  }

  onSignIn() {
    this.isLoggedIn = true;
  }

  onSignUp() {
    this.isLoggedIn = true;
  }

  onMyLibrary() { console.log('going to my library'); }

  onSignOut() {
    this.isLoggedIn = false;
  }
}
