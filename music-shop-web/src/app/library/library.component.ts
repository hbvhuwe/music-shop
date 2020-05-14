import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import {ApiService} from '../services/api.service';
import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-library',
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {
  private readonly pageTitle = "My library";
  userId: string = null;
  loading = true;
  error = false;

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private route: ActivatedRoute, private router: Router,
      private toolbarService: ToolbarService) {}

  ngOnInit() {
    this.userId = this.route.snapshot.paramMap.get('userId');
    this.toolbarService.setTitle(this.pageTitle);
  }
}
