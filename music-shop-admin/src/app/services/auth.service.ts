import {HttpClient} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import * as hash from 'hash.js';
import {Observable, Subject} from 'rxjs';

import {API_HOST} from '../config';
import {Admin} from '../model/admin.model';

@Injectable({providedIn: 'root'})
export class AuthService {
  isLoggedIn = new Subject<boolean>();

  constructor(private http: HttpClient) {}

  checkCredentials(auth: string): Observable<any> {
    const options = this.createOptions({Authorization: auth});
    return this.http.get(`${API_HOST}/admin/auth/check_credentials`, options);
  }

  login(login: string, password: string): Observable<any> {
    return this.http.get(`${API_HOST}/admin/auth/login`, {
      params: {
        login,
        password: this.hashOf(password),
      }
    });
  }

  getUserInfo(): Observable<Admin> {
    const options = this.createOptions({
      Authorization: localStorage.getItem('currentAuth'),
    });
    return this.http.get<Admin>(`${API_HOST}/admin/auth/user`, options);
  }

  private createOptions(headers) {
    return {
      headers: new HttpHeaders(headers),
    };
  }

  private hashOf(s: string): string {
    return hash.sha256().update(s).digest('hex');
  }
}
