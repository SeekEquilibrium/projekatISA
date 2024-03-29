import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  
  private _image_path = '../../assets/images/'
  private _api_url = 'http://localhost:8080';
  private _auth_url = 'http://localhost:8080/registration';
  private _user_url = this._api_url + '/user';
  private _cottage_url = this._api_url + '/cottage';

  private _login_url = this._auth_url + '/login';

  get login_url(): string {
    return this._login_url;
  }

  private _whoami_url = this._api_url + '/whoami';

  get whoami_url(): string {
    return this._whoami_url;
  }

  private _users_url = this._user_url + '/all';

  get users_url(): string {
    return this._users_url;
  }

  private _foo_url = this._api_url + '/foo';

  get foo_url(): string {
    return this._foo_url;
  }

  private _signup_url = this._auth_url + '/signup';

  get signup_url(): string {
    return this._signup_url;
  }

  public get_cottage_profile_url(name: string): string{
    return this._cottage_url = this._cottage_url + '/' + name;
  }

  public image_path(): string{
    return this._image_path;
  }
}
