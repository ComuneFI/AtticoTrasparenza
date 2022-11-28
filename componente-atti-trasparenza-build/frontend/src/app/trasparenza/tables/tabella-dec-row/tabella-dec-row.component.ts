import { Component, OnInit, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { CommonUtils } from './../../utils/common-utils';

import { NgbModal, NgbActiveModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { DettaglioDecBModalComponent } from './../../modals/dec/modal-dec.component';

@Component({
  selector: '[app-tabella-dec-row]',
  templateUrl: './tabella-dec-row.component.html',
  styleUrls: ['./tabella-dec-row.component.scss']
})
export class TabellaDecRowComponent implements OnInit {
  @Input() json_data: any;
  @Input() columns: string[];

  constructor(public translateService: TranslateService,
              public utils: CommonUtils,
              private modalService: NgbModal) { }

  ngOnInit() {
  }

  open() {
    const modalRef = this.modalService.open(DettaglioDecBModalComponent, {
      size: 'lg',
      // backdrop: 'static',
      windowClass: 'modal-xl'
    });
    modalRef.componentInstance.content = this.json_data;
  }

}
