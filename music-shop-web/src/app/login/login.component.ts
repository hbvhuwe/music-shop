import { Component, OnInit } from '@angular/core';

import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private readonly pageName = "Sign in";

  constructor(private toolbarService: ToolbarService) { }

  ngOnInit() {
    this.toolbarService.setTitle(this.pageName);
  }

}
