import { Component, Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Injectable()
export class CommonUtils {

  constructor(private translateService: TranslateService) { }

  getPathByTipoAtto(tipoAtto: string): string {
    for (const key in environment.atto.tipo) {
      const tipo = environment.atto.tipo;
      if (tipo[key].codice === tipoAtto) {
        return tipo[key].sezione;
      }
    }
    return null;
  }

  printWithMaxCharacterLimit(string: string) {
    if (string != null && typeof string === 'string') {
      if (string.length > environment.maxDisplayedCharacters) {
        string = string.substring(0, environment.maxDisplayedCharacters);
        string += '...';
      }
    }
    return string;
  }

  getQueryString() {
    let queryString = location.href;
    let index;

    if ( (index = queryString.lastIndexOf('?')) >= 0 ) {
      queryString = queryString.substring(index);
    }

    if ( (index = queryString.lastIndexOf('#')) >= 0 ) {
      queryString = queryString.substring(0, index);
    }

    return queryString;
  }

  getValueFromQueryString(queryString: string, attribute: string) {
    let index;
    const queryMap = {};
    let queryList;

    if ( (index = queryString.indexOf('?')) >= 0 ) {
      queryString = queryString.substring(index + 1);
    }

    queryList = queryString.split('&');

    for (const couple of queryList) {
      let attr;
      let value;

      if ( (index = couple.indexOf('=')) >= 0 ) {
        attr = couple.substring(0, index);
      } else {
        attr = couple;
      }

      if ( (index = couple.indexOf('=')) >= 0 ) {
        value = couple.substring(index + 1);
      }

      queryMap[attr] = value;
    }

    if (typeof queryMap[attribute] === 'undefined') {
      return '';
    } else {
      return queryMap[attribute];
    }
  }

  /* object empty check */
  isEmpty(obj: any): boolean {
    if (obj == null) {
      return true;
    }

    if (typeof obj === 'object') {
      const hasOwnProperty = Object.prototype.hasOwnProperty;

      if (obj == null) {
        return true;
      }

      if (obj.length > 0) {
        return false;
      } else if (obj.length <= 0) {
        return true;
      }

      if ( Object.keys(obj).length <= 0 ) {
        return true;
      }
      for (const key in obj) {
          if (hasOwnProperty.call(obj, key)) {
            return false;
          }
      }
    } else if (typeof obj === 'string') {
      if (obj.length <= 0) {
        return true;
      }
    } else if (Array.isArray(obj) && obj.length <= 0) {
      return true;
    }

    return false;
  }
}
