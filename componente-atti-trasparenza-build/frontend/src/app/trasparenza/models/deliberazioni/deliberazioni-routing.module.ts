import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Route, extract } from '@app/core';
import { DeliberazioniComponent } from './deliberazioni.component';

const routes: Routes = [
  Route.withShell([
    { path: 'deliberazioni', component: DeliberazioniComponent, data: { title: extract('deliberations') } }
  ])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class DeliberazioniRoutingModule { }
