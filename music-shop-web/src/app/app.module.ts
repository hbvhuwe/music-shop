import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
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
import {RegisterComponent} from './register/register.component';
import {LibraryComponent} from './library/library.component';

@NgModule({
  declarations: [
    AppComponent, AppBarComponent, LoginComponent, MainComponent,
    PageNotFoundComponent, GroupListComponent, AlbumListComponent,
    CompositionsListComponent, GroupDetailsComponent, AlbumDetailsComponent,
    RegisterComponent, LibraryComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, ReactiveFormsModule,
    AppRoutingModule, BrowserAnimationsModule, MatButtonModule,
    MatToolbarModule, MatIconModule, MatDividerModule, MatMenuModule,
    MatTabsModule, MatSnackBarModule, MatCardModule, MatProgressSpinnerModule,
    MatTableModule, MatFormFieldModule, MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
