import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: '[app-table-row]',
  templateUrl: './table-row.component.html',
  styleUrls: ['./table-row.component.scss']
})
export class TableRowComponent implements OnInit {
        
  @Input() character: any;
  @Input() columns: string[];
    
  @Output() clickLn: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

    onclick(col:any){
        //console.log("dal componentne", col);
        this.clickLn.emit(col);
    }
    
}

