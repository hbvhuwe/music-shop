import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import {ApiService} from '../services/api.service';
import {Group} from '../model/group.model';

import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-group-details',
  templateUrl: './group-details.component.html',
  styleUrls: ['./group-details.component.css']
})
export class GroupDetailsComponent implements OnInit {
  groupId: string = null;
  group: Group;
  loading = true;
  error = false;

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private route: ActivatedRoute, private router: Router,
      private toolbarService: ToolbarService) {}

  ngOnInit() {
    this.groupId = this.route.snapshot.paramMap.get('groupId');
    this.toolbarService.setTitle('Group details');
    this.loadGroup();
  }

  private loadGroup() {
    this.api.getGroup(this.groupId)
        .subscribe(
            result => {
              this.group = result;
              this.toolbarService.setTitle(this.group.name);
              this.loading = false;
              this.error = false;
            },
            error => {
              let s = this.openSnackBar("Network error", "Try again");
              this.loading = false;
              this.error = true;
              s.onAction().subscribe(() => {
                this.loading = true;
                this.loadGroup();
              });
            })
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<any> {
    return this.snackbar.open(message, action, {
      duration: 2000,
    });
  }
}
