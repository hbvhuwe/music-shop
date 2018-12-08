import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatSnackBar, MatSnackBarRef} from '@angular/material';
import {ApiService} from '../services/api.service';
import {Album} from '../model/album.model';
import {Group} from '../model/group.model';

@Component({
  selector : 'app-album-list',
  templateUrl : './album-list.component.html',
  styleUrls : [ './album-list.component.css' ]
})
export class AlbumListComponent implements OnInit {
  @Input('show') show: string;
  @Input('groupId') groupId: string;

  listEmpty: boolean = false;
  loading: boolean = true;
  albumList: Album[];

  constructor(private snackbar: MatSnackBar, private api: ApiService,
              private route: ActivatedRoute, private router: Router) {}

  ngOnInit() { this.loadList(); }

  private loadList() {
    if (this.show && this.show === 'all') {
      this.api.getAllAlbums().subscribe(
          result => {
            this.listEmpty = result.length === 0;
            this.albumList = result;
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
    } else if (this.show && this.show !== 'all') {
      this.api.getAlbumsForUser(this.show).subscribe(
          result => {
            this.listEmpty = result.length === 0;
            this.albumList = result;
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
    } else if (this.groupId) {
      this.api.getAlbumsForGroup(this.groupId)
          .subscribe(
              result => {
                this.listEmpty = result.length === 0;
                this.albumList = result;
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
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<any> {
    return this.snackbar.open(message, action, {
      duration : 2000,
    });
  }

  onBuy(album: Album) {}

  onAlbumClick(album: Album) {
    this.router.navigate([ `/albums/${album.diskId}` ]);
  }
}
