import { Component, OnInit, Input } from '@angular/core';

import { AdventureTimeService } from '../services/adventure-time.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit {
  characters: Observable<any[]>;
  columns: string[];

  constructor(private atService: AdventureTimeService) { }

  ngOnInit() {
      this.columns = this.atService.getColumns();    
      this.characters = this.atService.getCharacters();
  }

    listener(col:any) {
        //console.log("ciao", col)   ;
    }
}
