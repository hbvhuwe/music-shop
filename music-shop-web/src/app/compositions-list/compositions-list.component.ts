import {Component, OnInit, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MatSnackBar, MatSnackBarRef} from '@angular/material';
import {ApiService} from '../services/api.service';
import {Composition} from '../model/composition.model';

@Component({
  selector : 'app-compositions-list',
  templateUrl : './compositions-list.component.html',
  styleUrls : [ './compositions-list.component.css' ]
})
export class CompositionsListComponent implements OnInit {
  @Input('show') show: string;
  @Input('albumId') albumId : string;

  compositionsList: Composition[];
  displayedColumns: string[] = [ 'diskId', 'name', 'presentDate', 'duration' ];

  listEmpty: boolean = false;
  loading: boolean = true;

  constructor(private snackbar: MatSnackBar, private api: ApiService,
              private route: ActivatedRoute, private router: Router) {}

  ngOnInit() { this.loadList(); }

  private loadList() {
    if (this.show && this.show === 'all') {
      this.api.getAllCompositions().subscribe(
          result => {
            this.listEmpty = result.length === 0;
            this.compositionsList = result;
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
      this.api.getCompositionsForUser(this.show).subscribe(
          result => {
            this.listEmpty = result.length === 0;
            this.compositionsList = result;
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
    } else if (this.albumId) {
      this.api.getCompositionsForAlbum(this.albumId)
          .subscribe(
              result => {
                this.listEmpty = result.length === 0;
                this.compositionsList = result;
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
}
