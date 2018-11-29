import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpHeaders} from '@angular/common/http';

import * as hash from 'hash.js';

import {API_HOST} from '../config';

@Injectable({providedIn: 'root'})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(clientId: number, password: string): Observable<any> {
    password = this.hashOf(password);
    const options = this.createOptions({
        'Authorization': btoa(`${clientId}:${password}`)
    });
    return this.http.get(`${API_HOST}/check_credentials`, options)
  }

  private createOptions(headers) {
    return {
        headers: new HttpHeaders(headers)
    };
  }

  private hashOf(s: string): string {
    return hash.sha256().update(s).digest('hex');
  }
}