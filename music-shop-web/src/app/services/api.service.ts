import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Group} from '../model/group.model';
import {Album} from '../model/album.model';
import {Composition} from '../model/composition.model';

import {API_HOST} from '../config';

@Injectable({providedIn : 'root'})
export class ApiService {

  constructor(private http: HttpClient) {}

  getAllGroups(): Observable<Group[]> {
    return this.http.get<Group[]>(`${API_HOST}/groups/`);
  }

  getGroup(groupId: string): Observable<Group> {
    return this.http.get<Group>(`${API_HOST}/groups/${groupId}`);
  }

  getAllAlbums(): Observable<Album[]> {
    return this.http.get<Album[]>(`${API_HOST}/disks/`);
  }

  getAlbum(albumId: string): Observable<Album> {
    return this.http.get<Album>(`${API_HOST}/disks/${albumId}`);
  }

  getAlbumsForGroup(groupId: string): Observable<Album[]> {
    return this.http.get<Album[]>(`${API_HOST}/groups/${groupId}/disks`);
  }

  getAlbumsForUser(userId: string): Observable<Album[]> {
    return this.http.get<Album[]>(`${API_HOST}/disks/library/${userId}`);
  }

  getAllCompositions(): Observable<Composition[]> {
    return this.http.get<Composition[]>(`${API_HOST}/compositions/`);
  }

  getCompositionsForUser(userId: string): Observable<Composition[]> {
    return this.http.get<Composition[]>(`${API_HOST}/compositions/library/${userId}`);
  }

  getCompositionsForAlbum(albumId: string): Observable<Composition[]> {
    return this.http.get<Composition[]>(`${API_HOST}/disks/${albumId}/compositions`);
  }
}
