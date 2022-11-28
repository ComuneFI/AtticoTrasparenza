import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-dec-bmodal',
  templateUrl: './modal-dec.component.html',
  styleUrls: ['./modal-dec.component.scss']
})
export class DettaglioDecBModalComponent implements OnInit, OnDestroy {
  @Input() content: any;

  constructor(public activeModal: NgbActiveModal,
  			  public translateService: TranslateService) {}

  ngOnInit() {
  }
  
  ngOnDestroy() {
  }
}
