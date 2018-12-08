import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {MainComponent} from './main/main.component';
import {LoginComponent} from './login/login.component';
import {AlbumListComponent} from './album-list/album-list.component';
import {CompositionsListComponent} from './compositions-list/compositions-list.component';
import {GroupDetailsComponent} from './group-details/group-details.component';
import {AlbumDetailsComponent} from './album-details/album-details.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {RegisterComponent} from './register/register.component';

const routes: Routes = [
  {path: 'main', component: MainComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '', redirectTo: '/main', pathMatch: 'full'},
  {path: 'groups/:groupId', component: GroupDetailsComponent},
  {path: 'albums/:albumId', component: AlbumDetailsComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({imports: [RouterModule.forRoot(routes)], exports: [RouterModule]})
export class AppRoutingModule {
}
