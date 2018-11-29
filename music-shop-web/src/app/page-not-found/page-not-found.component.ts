import {Component, OnInit} from '@angular/core';

import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector : 'app-page-not-found',
  templateUrl : './page-not-found.component.html',
  styleUrls : [ './page-not-found.component.css' ]
})
export class PageNotFoundComponent implements OnInit {
  private readonly pageName = "Not found";

  constructor(private toolbarService: ToolbarService) {}

  ngOnInit() { this.toolbarService.setTitle(this.pageName); }
}
