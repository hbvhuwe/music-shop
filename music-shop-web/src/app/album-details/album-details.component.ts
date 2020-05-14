import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { MatSnackBar, MatSnackBarRef } from '@angular/material/snack-bar';
import {ApiService} from '../services/api.service';
import {Album} from '../model/album.model';

import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-album-details',
  templateUrl: './album-details.component.html',
  styleUrls: ['./album-details.component.css']
})
export class AlbumDetailsComponent implements OnInit {
  albumId: string = null;
  album: Album;
  loading = true;
  error = false;
  alreadyPurchased = false;

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private route: ActivatedRoute, private router: Router,
      private toolbarService: ToolbarService) {}

  ngOnInit() {
    this.albumId = this.route.snapshot.paramMap.get('albumId');
    this.toolbarService.setTitle('Album details');
    this.loadAlbum()
  }

  private loadAlbum() {
    this.api.getAlbum(this.albumId)
        .subscribe(
            result => {
              this.album = result;
              this.toolbarService.setTitle(this.album.name);
              this.loading = false;
              this.error = false;
            },
            error => {
              let s = this.openSnackBar("Network error", "Try again");
              this.loading = false;
              this.error = true;
              s.onAction().subscribe(() => {
                this.loading = true;
                this.loadAlbum();
              });
            });
    this.api.getAlbumsForUser(localStorage.getItem('currentUserId'))
        .subscribe(
            result => {
              let r = result.find((e) => e.diskId.toString() === this.albumId);
              if (r) {
                this.alreadyPurchased = true;
              } else {
                this.alreadyPurchased = false;
              }
            },
            error => {
              // make sure that user cannot purchase in case of error
              this.alreadyPurchased = true;
            });
  }

  onBuy() {
    this.api.purchaseAlbum(this.album.diskId, this.album.price)
        .subscribe(result => {
          if (result.status === 'success') {
            this.openSnackBar('Successfully purchased', 'Ok');
            this.alreadyPurchased = true;
          } else {
            this.openSnackBar('Failed purchase: ' + result.message, 'Ok');
          }
        });
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<any> {
    return this.snackbar.open(message, action, {
      duration: 2000,
    });
  }
}
