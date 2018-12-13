import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {HttpHeaders} from '@angular/common/http';

import {Client} from '../model/client.model';

import * as hash from 'hash.js';

import {API_HOST} from '../config';

@Injectable({providedIn: 'root'})
export class AuthService {
  isLoggedIn = new Subject<boolean>();

  constructor(private http: HttpClient) {}

  checkCredentials(auth: string): Observable<any> {
    const options = this.createOptions({'Authorization': auth});
    return this.http.get(`${API_HOST}/auth/check_credentials`, options)
  }

  login(login: string, password: string): Observable<any> {
    return this.http.get(
        `${API_HOST}/auth/login`,
        {params: {login: login, password: this.hashOf(password)}});
  }

  getUserInfo(): Observable<Client> {
    const options = this.createOptions(
        {'Authorization': localStorage.getItem('currentAuth')});
    return this.http.get<Client>(`${API_HOST}/auth/user`, options);
  }

  register(client: Client) {
    return this.http.put(`${API_HOST}/auth/sign_up`, null, {
      params: {
        Name: client.name,
        Login: client.login,
        Surname: client.surname,
        Password: this.hashOf(client.password)
      }
    });
  }

  private createOptions(headers) { return {headers: new HttpHeaders(headers)}; }

  private hashOf(s: string): string {
    return hash.sha256().update(s).digest('hex');
  }
}
