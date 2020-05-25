import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MatSnackBar, MatSnackBarRef} from '@angular/material/snack-bar';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {Router} from '@angular/router';

import {Composition} from '../model/composition.model';
import {ApiService} from '../services/api.service';

@Component({
  selector: 'app-compositions-list',
  templateUrl: './compositions-list.component.html',
  styleUrls: ['./compositions-list.component.css']
})
export class CompositionsListComponent implements OnInit {
  compositions: Composition[];
  displayedColumns: string[] =
      ['compositionId', 'duration', 'name', 'presentDate', 'diskId'];

  dataSource = new MatTableDataSource(this.compositions);

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  form: FormGroup;

  durationFormControl = new FormControl('', [
    Validators.required,
  ]);
  nameFormControl = new FormControl('', [
    Validators.required,
  ]);
  presentDateFormControl = new FormControl('', [
    Validators.required,
  ]);
  diskIdFormControl = new FormControl('', [
    Validators.required,
  ]);

  constructor(
      private snackbar: MatSnackBar, private api: ApiService,
      private fb: FormBuilder, private router: Router) {}

  ngOnInit() {
    this.loadList();
    this.dataSource.sort = this.sort;
    this.form = this.fb.group({
      duration: this.durationFormControl,
      name: this.nameFormControl,
      presentDate: this.presentDateFormControl,
      diskId: this.diskIdFormControl,
    });
  }

  private loadList() {
    this.api.getAllCompositions().subscribe(
        result => {
          this.compositions = result;
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
        .addComposition(
            this.durationFormControl.value.toString(),
            this.nameFormControl.value.toString(),
            this.presentDateFormControl.value.toString(),
            this.diskIdFormControl.value.toString())
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
