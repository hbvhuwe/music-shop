import {Component, OnInit} from '@angular/core';

import {ToolbarService} from '../services/toolbar.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  private readonly pageName = "Music shop";

  constructor(private toolbarService: ToolbarService) {}

  ngOnInit() { this.toolbarService.setTitle(this.pageName); }
}
