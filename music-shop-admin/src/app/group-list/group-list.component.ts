import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar, MatSnackBarRef} from '@angular/material/snack-bar';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Router} from '@angular/router';

import {Group} from '../model/group.model';
import {ApiService} from '../services/api.service';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {
  groups: Group[];
  displayedColumns: string[] = ['groupId', 'musician', 'name', 'style'];

  dataSource = new MatTableDataSource(this.groups);

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  form: FormGroup;

  musicianFormControl = new FormControl('', [
    Validators.required,
  ]);
  nameFormControl = new FormControl('', [
    Validators.required,
  ]);
  presentDateFormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    this.loadList();
    this.dataSource.sort = this.sort;
    this.form = this.fb.group({
      musician: this.musicianFormControl,
      name: this.nameFormControl,
      presentDate: this.presentDateFormControl,
    });
  }

  private loadList() {
    this.api.getAllGroups().subscribe(
        result => {
          this.groups = result;
        },
        error => {
          const s = this.openSnackBar('Network error', 'Try again');
          s.onAction().subscribe(() => {
            this.loadList();
          });
        });
  }

  onSubmit() {
    this.api
        .addGroup(
            this.nameFormControl.value.toString(),
            this.musicianFormControl.value.toString(),
            this.presentDateFormControl.value.toString())
        .subscribe(_ => {
          this.loadList();
        });
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<any> {
    return this.snackbar.open(message, action, {
      duration: 2000,
    });
  }
}
