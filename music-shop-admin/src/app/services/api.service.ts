import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

import {API_HOST} from '../config';
import {Album} from '../model/album.model';
import {Composition} from '../model/composition.model';
import {Group} from '../model/group.model';

@Injectable({providedIn: 'root'})
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

  getAllCompositions(): Observable<Composition[]> {
    return this.http.get<Composition[]>(`${API_HOST}/compositions/`);
  }

  getCompositionsForAlbum(albumId: string): Observable<Composition[]> {
    return this.http.get<Composition[]>(
        `${API_HOST}/disks/${albumId}/compositions`);
  }

  private createOptions(headers) {
    return {headers: new HttpHeaders(headers)};
  }
}
