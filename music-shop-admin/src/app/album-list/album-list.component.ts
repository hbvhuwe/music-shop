import {Component, Input, OnInit, ViewChild} from '@angular/core';
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

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private router: Router) {}

  ngOnInit() {
    this.loadList();
    this.dataSource.sort = this.sort;
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

  openSnackBar(message: string, action: string): MatSnackBarRef<any> {
    return this.snackbar.open(message, action, {
      duration: 2000,
    });
  }
}
