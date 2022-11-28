import { Injectable } from '@angular/core';

/*import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/delay';*/
//import { of } from 'rxjs/observable/of';
//import { of } from 'rxjs';
import { Observable, Subscription, of } from 'rxjs';
import { delay } from 'rxjs/operators';

import { CHARACTERS } from './mock-data';

@Injectable({
  providedIn: 'root'
})
export class AdventureTimeService {

    constructor() { }
    
    private columns = ["name", "age", "species", "occupation"];
    
    getCharacters(): Observable<any[]>{
      return of(CHARACTERS).pipe(delay(100));
    }
    
    getColumns(): string[]{
      return this.columns;
    }
}
