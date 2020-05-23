import {Injectable} from '@angular/core';
import {OnDestroy} from '@angular/core';
import {Subject} from 'rxjs';

@Injectable({providedIn: 'root'})
export class ToolbarService implements OnDestroy {
  toolbarTitle = new Subject<string>();

  constructor() {
    this.toolbarTitle.next('Default');
  }

  setTitle(title: string) {
    this.toolbarTitle.next(title);
  }

  ngOnDestroy() {
    this.toolbarTitle.complete();
  }
}
