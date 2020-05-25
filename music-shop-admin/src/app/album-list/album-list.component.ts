import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar, MatSnackBarRef} from '@angular/material/snack-bar';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Router} from '@angular/router';

import {Album} from '../model/album.model';
import {ApiService} from '../services/api.service';

@Component({
  selector: 'app-album-list',
  templateUrl: './album-list.component.html',
  styleUrls: ['./album-list.component.css']
})
export class AlbumListComponent implements OnInit {
  disks: Album[];
  displayedColumns: string[] =
      ['diskId', 'amount', 'name', 'presentDate', 'price', 'groupId'];

  dataSource = new MatTableDataSource(this.disks);

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  form: FormGroup;

  amountFormControl = new FormControl('', [
    Validators.required,
  ]);
  nameFormControl = new FormControl('', [
    Validators.required,
  ]);
  imageFormControl = new FormControl('', [
    Validators.required,
  ]);
  presentDateFormControl = new FormControl('', [
    Validators.required,
  ]);
  priceFormControl = new FormControl('', [
    Validators.required,
  ]);
  groupIdFormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    this.loadList();
    this.dataSource.sort = this.sort;
    this.form = this.fb.group({
      amount: this.amountFormControl,
      name: this.nameFormControl,
      image: this.imageFormControl,
      presentDate: this.presentDateFormControl,
      price: this.priceFormControl,
      groupId: this.groupIdFormControl,
    });
  }

  private loadList() {
    this.api.getAllAlbums().subscribe(
        result => {
          this.disks = result;
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
        .addDisk(
            Number(this.amountFormControl.value.toString()),
            this.nameFormControl.value.toString(),
            this.imageFormControl.value.toString(),
            this.presentDateFormControl.value.toString(),
            this.priceFormControl.value.toString(),
            this.groupIdFormControl.value.toString())
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
