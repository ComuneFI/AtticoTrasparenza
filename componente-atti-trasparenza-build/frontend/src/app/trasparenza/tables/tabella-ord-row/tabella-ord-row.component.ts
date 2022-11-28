import { Component, OnInit, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { CommonUtils } from './../../utils/common-utils';

import { NgbModal, NgbActiveModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { DettaglioOrdBModalComponent } from './../../modals/ord/modal-ord.component';

@Component({
  selector: '[app-tabella-ord-row]',
  templateUrl: './tabella-ord-row.component.html',
  styleUrls: ['./tabella-ord-row.component.scss']
})
export class TabellaOrdRowComponent implements OnInit {
  @Input() json_data: any;
  @Input() columns: string[];

  constructor(public translateService: TranslateService,
              public utils: CommonUtils,
              private modalService: NgbModal) { }

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(DettaglioOrdBModalComponent, {
      size: 'lg',
      // backdrop: 'static',
      windowClass: 'modal-xl'
    });
    modalRef.componentInstance.content = this.json_data;
  }

}
