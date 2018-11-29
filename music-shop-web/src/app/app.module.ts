import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatToolbarModule,
  MatIconModule,
  MatDividerModule,
  MatMenuModule,
  MatTabsModule
} from '@angular/material';
import {AppBarComponent} from './app-bar/app-bar.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [AppComponent, AppBarComponent, LoginComponent, MainComponent, PageNotFoundComponent],
  imports: [
    BrowserModule, HttpClientModule, AppRoutingModule, BrowserAnimationsModule,
    MatButtonModule, MatToolbarModule, MatIconModule, MatDividerModule,
    MatMenuModule, MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
