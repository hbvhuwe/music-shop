import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatToolbarModule,
  MatIconModule,
  MatDividerModule,
  MatMenuModule,
  MatTabsModule,
  MatSnackBarModule,
  MatCardModule,
  MatProgressSpinnerModule,
  MatTableModule,
  MatFormFieldModule,
  MatInputModule
} from '@angular/material';
import {AppBarComponent} from './app-bar/app-bar.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {GroupListComponent} from './group-list/group-list.component';
import {AlbumListComponent} from './album-list/album-list.component';
import {
  CompositionsListComponent
} from './compositions-list/compositions-list.component';
import {GroupDetailsComponent} from './group-details/group-details.component';
import {AlbumDetailsComponent} from './album-details/album-details.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations : [
    AppComponent, AppBarComponent, LoginComponent, MainComponent,
    PageNotFoundComponent, GroupListComponent, AlbumListComponent,
    CompositionsListComponent, GroupDetailsComponent, AlbumDetailsComponent, RegisterComponent
  ],
  imports : [
    BrowserModule, HttpClientModule, FormsModule, ReactiveFormsModule,
    AppRoutingModule, BrowserAnimationsModule, MatButtonModule,
    MatToolbarModule, MatIconModule, MatDividerModule, MatMenuModule,
    MatTabsModule, MatSnackBarModule, MatCardModule, MatProgressSpinnerModule,
    MatTableModule, MatFormFieldModule, MatInputModule
  ],
  providers : [],
  bootstrap : [ AppComponent ]
})
export class AppModule {
}
