import { Component, OnInit, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { CommonUtils } from './../../utils/common-utils';

import { NgbModal, NgbActiveModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { DettaglioAcBModalComponent } from './../../modals/ac/modal-ac.component';

@Component({
  selector: '[app-tabella-ac-row]',
  templateUrl: './tabella-ac-row.component.html',
  styleUrls: ['./tabella-ac-row.component.scss']
})
export class TabellaAcRowComponent implements OnInit {
  @Input() json_data: any;
  @Input() columns: string[];

  constructor(public translateService: TranslateService,
              public utils: CommonUtils,
              private modalService: NgbModal) { }

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(DettaglioAcBModalComponent, {
      size: 'lg',
      // backdrop: 'static',
      windowClass: 'modal-xl'
    });
    modalRef.componentInstance.content = this.json_data;
  }

}
