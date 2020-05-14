import {Component, OnInit, Input} from '@angular/core';
import {Router} from '@angular/router';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import {ApiService} from '../services/api.service';
import {Group} from '../model/group.model';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {
  listEmpty: boolean = false;
  loading: boolean = true;
  groupsList: Group[];

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private router: Router) {}

  ngOnInit() { this.loadList(); }

  private loadList() {
    this.api.getAllGroups().subscribe(
        result => {
          this.listEmpty = result.length === 0;
          this.groupsList = result;
          this.loading = false;
        },
        error => {
          let s = this.openSnackBar("Network error", "Try again");
          this.loading = false;
          s.onAction().subscribe(() => {
            this.loading = true;
            this.loadList();
          });
        });
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<any> {
    return this.snackbar.open(message, action, {
      duration: 2000,
    });
  }

  onGroupClick(group: Group) {
    this.router.navigate([`/groups/${group.groupId}`]);
  }
}
