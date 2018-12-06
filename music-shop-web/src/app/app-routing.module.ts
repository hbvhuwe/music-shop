import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {MainComponent} from './main/main.component';
import {LoginComponent} from './login/login.component';
import {AlbumListComponent} from './album-list/album-list.component';
import {CompositionsListComponent} from './compositions-list/compositions-list.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';

const routes: Routes = [
  {path: 'main', component: MainComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/main', pathMatch: 'full'},
  {path: 'groups/:groupId/albums', component: AlbumListComponent},
  {path: 'albums/:diskId/compositions', component: CompositionsListComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({imports: [RouterModule.forRoot(routes)], exports: [RouterModule]})
export class AppRoutingModule {
}
