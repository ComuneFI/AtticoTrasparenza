import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-ord-bmodal',
  templateUrl: './modal-ord.component.html',
  styleUrls: ['./modal-ord.component.scss']
})
export class DettaglioOrdBModalComponent implements OnInit, OnDestroy {
  @Input() content: any;

  constructor(public activeModal: NgbActiveModal,
              public translateService: TranslateService) {}

  ngOnInit() {
  }

  ngOnDestroy() {
  }
}
