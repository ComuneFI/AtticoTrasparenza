import { Component, OnInit, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { CommonUtils } from './../../utils/common-utils';

import { NgbModal, NgbActiveModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { DettaglioDelBModalComponent } from './../../modals/del/modal-del.component';

@Component({
  selector: '[app-tabella-del-row]',
  templateUrl: './tabella-del-row.component.html',
  styleUrls: ['./tabella-del-row.component.scss']
})
export class TabellaDelRowComponent implements OnInit {
  @Input() json_data: any;
  @Input() columns: string[];

  constructor(public translateService: TranslateService,
              public utils: CommonUtils,
              private modalService: NgbModal) { }

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(DettaglioDelBModalComponent, {
      size: 'lg',
      // backdrop: 'static',
      windowClass: 'modal-xl'
    });
    modalRef.componentInstance.content = this.json_data;
  }

}
