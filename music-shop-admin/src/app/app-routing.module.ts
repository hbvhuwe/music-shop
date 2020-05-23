import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

// import {AlbumDetailsComponent} from
// './album-details/album-details.component'; import {AlbumListComponent} from
// './album-list/album-list.component'; import {CompositionsListComponent} from
// './compositions-list/compositions-list.component';
// import {GroupDetailsComponent} from
// './group-details/group-details.component';
import {LoginComponent} from './login/login.component';
import {MainComponent} from './main/main.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';

const routes: Routes = [
  {path: 'main', component: MainComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/main', pathMatch: 'full'},
  // {path: 'groups/:groupId', component: GroupDetailsComponent},
  // {path: 'albums/:albumId', component: AlbumDetailsComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({imports: [RouterModule.forRoot(routes)], exports: [RouterModule]})
export class AppRoutingModule {
}
