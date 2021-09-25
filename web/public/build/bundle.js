var app=function(){"use strict";function t(){}function n(t,n){for(const e in n)t[e]=n[e];return t}function e(t){return t()}function o(){return Object.create(null)}function r(t){t.forEach(e)}function i(t){return"function"==typeof t}function s(t,n){return t!=t?n==n:t!==n||t&&"object"==typeof t||"function"==typeof t}function c(t,n){t.appendChild(n)}function l(t,n,e){t.insertBefore(n,e||null)}function a(t){t.parentNode.removeChild(t)}function u(t,n){for(let e=0;e<t.length;e+=1)t[e]&&t[e].d(n)}function d(t){return document.createElement(t)}function p(t){return document.createTextNode(t)}function f(){return p(" ")}function m(){return p("")}function h(t,n,e,o){return t.addEventListener(n,e,o),()=>t.removeEventListener(n,e,o)}function b(t,n,e){null==e?t.removeAttribute(n):t.getAttribute(n)!==e&&t.setAttribute(n,e)}function g(t,n){n=""+n,t.wholeText!==n&&(t.data=n)}function y(t,n){t.value=null==n?"":n}function v(t,n,e){t.classList[e?"add":"remove"](n)}let $;function x(t){$=t}function w(){if(!$)throw new Error("Function called outside component initialization");return $}function C(t){w().$$.on_mount.push(t)}function k(){const t=w();return(n,e)=>{const o=t.$$.callbacks[n];if(o){const r=function(t,n){const e=document.createEvent("CustomEvent");return e.initCustomEvent(t,!1,!1,n),e}(n,e);o.slice().forEach((n=>{n.call(t,r)}))}}}function E(t,n){const e=t.$$.callbacks[n.type];e&&e.slice().forEach((t=>t(n)))}const j=[],_=[],T=[],L=[],S=Promise.resolve();let O=!1;function M(){O||(O=!0,S.then(I))}function A(){return M(),S}function N(t){T.push(t)}let H=!1;const R=new Set;function I(){if(!H){H=!0;do{for(let t=0;t<j.length;t+=1){const n=j[t];x(n),P(n.$$)}for(x(null),j.length=0;_.length;)_.pop()();for(let t=0;t<T.length;t+=1){const n=T[t];R.has(n)||(R.add(n),n())}T.length=0}while(j.length);for(;L.length;)L.pop()();O=!1,H=!1,R.clear()}}function P(t){if(null!==t.fragment){t.update(),r(t.before_update);const n=t.dirty;t.dirty=[-1],t.fragment&&t.fragment.p(t.ctx,n),t.after_update.forEach(N)}}const q=new Set;let F;function z(){F={r:0,c:[],p:F}}function D(){F.r||r(F.c),F=F.p}function Y(t,n){t&&t.i&&(q.delete(t),t.i(n))}function X(t,n,e,o){if(t&&t.o){if(q.has(t))return;q.add(t),F.c.push((()=>{q.delete(t),o&&(e&&t.d(1),o())})),t.o(n)}}function B(t,n){const e={},o={},r={$$scope:1};let i=t.length;for(;i--;){const s=t[i],c=n[i];if(c){for(const t in s)t in c||(o[t]=1);for(const t in c)r[t]||(e[t]=c[t],r[t]=1);t[i]=c}else for(const t in s)r[t]=1}for(const t in o)t in e||(e[t]=void 0);return e}function J(t){return"object"==typeof t&&null!==t?t:{}}function U(t){t&&t.c()}function W(t,n,o){const{fragment:s,on_mount:c,on_destroy:l,after_update:a}=t.$$;s&&s.m(n,o),N((()=>{const n=c.map(e).filter(i);l?l.push(...n):r(n),t.$$.on_mount=[]})),a.forEach(N)}function G(t,n){const e=t.$$;null!==e.fragment&&(r(e.on_destroy),e.fragment&&e.fragment.d(n),e.on_destroy=e.fragment=null,e.ctx=[])}function K(n,e,i,s,c,l,u=[-1]){const d=$;x(n);const p=e.props||{},f=n.$$={fragment:null,ctx:null,props:l,update:t,not_equal:c,bound:o(),on_mount:[],on_destroy:[],before_update:[],after_update:[],context:new Map(d?d.$$.context:[]),callbacks:o(),dirty:u,skip_bound:!1};let m=!1;if(f.ctx=i?i(n,p,((t,e,...o)=>{const r=o.length?o[0]:e;return f.ctx&&c(f.ctx[t],f.ctx[t]=r)&&(!f.skip_bound&&f.bound[t]&&f.bound[t](r),m&&function(t,n){-1===t.$$.dirty[0]&&(j.push(t),M(),t.$$.dirty.fill(0)),t.$$.dirty[n/31|0]|=1<<n%31}(n,t)),e})):[],f.update(),m=!0,r(f.before_update),f.fragment=!!s&&s(f.ctx),e.target){if(e.hydrate){const t=function(t){return Array.from(t.childNodes)}(e.target);f.fragment&&f.fragment.l(t),t.forEach(a)}else f.fragment&&f.fragment.c();e.intro&&Y(n.$$.fragment),W(n,e.target,e.anchor),I()}x(d)}class Q{$destroy(){G(this,1),this.$destroy=t}$on(t,n){const e=this.$$.callbacks[t]||(this.$$.callbacks[t]=[]);return e.push(n),()=>{const t=e.indexOf(n);-1!==t&&e.splice(t,1)}}$set(t){var n;this.$$set&&(n=t,0!==Object.keys(n).length)&&(this.$$.skip_bound=!0,this.$$set(t),this.$$.skip_bound=!1)}}const V=[];function Z(t,n){return{subscribe:tt(t,n).subscribe}}function tt(n,e=t){let o;const r=[];function i(t){if(s(n,t)&&(n=t,o)){const t=!V.length;for(let t=0;t<r.length;t+=1){const e=r[t];e[1](),V.push(e,n)}if(t){for(let t=0;t<V.length;t+=2)V[t][0](V[t+1]);V.length=0}}}return{set:i,update:function(t){i(t(n))},subscribe:function(s,c=t){const l=[s,c];return r.push(l),1===r.length&&(o=e(i)||t),s(n),()=>{const t=r.indexOf(l);-1!==t&&r.splice(t,1),0===r.length&&(o(),o=null)}}}}function nt(n,e,o){const s=!Array.isArray(n),c=s?[n]:n,l=e.length<2;return Z(o,(n=>{let o=!1;const a=[];let u=0,d=t;const p=()=>{if(u)return;d();const o=e(s?a[0]:a,n);l?n(o):d=i(o)?o:t},f=c.map(((n,e)=>function(n,...e){if(null==n)return t;const o=n.subscribe(...e);return o.unsubscribe?()=>o.unsubscribe():o}(n,(t=>{a[e]=t,u&=~(1<<e),o&&p()}),(()=>{u|=1<<e}))));return o=!0,p(),function(){r(f),d()}}))}function et(t){let e,o,r;const i=[t[2]];var s=t[0];function c(t){let e={};for(let t=0;t<i.length;t+=1)e=n(e,i[t]);return{props:e}}return s&&(e=new s(c()),e.$on("routeEvent",t[7])),{c(){e&&U(e.$$.fragment),o=m()},m(t,n){e&&W(e,t,n),l(t,o,n),r=!0},p(t,n){const r=4&n?B(i,[J(t[2])]):{};if(s!==(s=t[0])){if(e){z();const t=e;X(t.$$.fragment,1,0,(()=>{G(t,1)})),D()}s?(e=new s(c()),e.$on("routeEvent",t[7]),U(e.$$.fragment),Y(e.$$.fragment,1),W(e,o.parentNode,o)):e=null}else s&&e.$set(r)},i(t){r||(e&&Y(e.$$.fragment,t),r=!0)},o(t){e&&X(e.$$.fragment,t),r=!1},d(t){t&&a(o),e&&G(e,t)}}}function ot(t){let e,o,r;const i=[{params:t[1]},t[2]];var s=t[0];function c(t){let e={};for(let t=0;t<i.length;t+=1)e=n(e,i[t]);return{props:e}}return s&&(e=new s(c()),e.$on("routeEvent",t[6])),{c(){e&&U(e.$$.fragment),o=m()},m(t,n){e&&W(e,t,n),l(t,o,n),r=!0},p(t,n){const r=6&n?B(i,[2&n&&{params:t[1]},4&n&&J(t[2])]):{};if(s!==(s=t[0])){if(e){z();const t=e;X(t.$$.fragment,1,0,(()=>{G(t,1)})),D()}s?(e=new s(c()),e.$on("routeEvent",t[6]),U(e.$$.fragment),Y(e.$$.fragment,1),W(e,o.parentNode,o)):e=null}else s&&e.$set(r)},i(t){r||(e&&Y(e.$$.fragment,t),r=!0)},o(t){e&&X(e.$$.fragment,t),r=!1},d(t){t&&a(o),e&&G(e,t)}}}function rt(t){let n,e,o,r;const i=[ot,et],s=[];function c(t,n){return t[1]?0:1}return n=c(t),e=s[n]=i[n](t),{c(){e.c(),o=m()},m(t,e){s[n].m(t,e),l(t,o,e),r=!0},p(t,[r]){let l=n;n=c(t),n===l?s[n].p(t,r):(z(),X(s[l],1,1,(()=>{s[l]=null})),D(),e=s[n],e?e.p(t,r):(e=s[n]=i[n](t),e.c()),Y(e,1),e.m(o.parentNode,o))},i(t){r||(Y(e),r=!0)},o(t){X(e),r=!1},d(t){s[n].d(t),t&&a(o)}}}function it(){const t=window.location.href.indexOf("#/");let n=t>-1?window.location.href.substr(t+1):"/";const e=n.indexOf("?");let o="";return e>-1&&(o=n.substr(e+1),n=n.substr(0,e)),{location:n,querystring:o}}const st=Z(null,(function(t){t(it());const n=()=>{t(it())};return window.addEventListener("hashchange",n,!1),function(){window.removeEventListener("hashchange",n,!1)}}));nt(st,(t=>t.location)),nt(st,(t=>t.querystring));async function ct(t){if(!t||t.length<1||"/"!=t.charAt(0)&&0!==t.indexOf("#/"))throw Error("Invalid parameter location");await A(),history.replaceState({scrollX:window.scrollX,scrollY:window.scrollY},void 0,void 0),window.location.hash=("#"==t.charAt(0)?"":"#")+t}async function lt(){await A(),window.history.back()}function at(t,n,e){let{routes:o={}}=n,{prefix:r=""}=n,{restoreScrollState:i=!1}=n;class s{constructor(t,n){if(!n||"function"!=typeof n&&("object"!=typeof n||!0!==n._sveltesparouter))throw Error("Invalid component object");if(!t||"string"==typeof t&&(t.length<1||"/"!=t.charAt(0)&&"*"!=t.charAt(0))||"object"==typeof t&&!(t instanceof RegExp))throw Error('Invalid value for "path" argument');const{pattern:e,keys:o}=function(t,n){if(t instanceof RegExp)return{keys:!1,pattern:t};var e,o,r,i,s=[],c="",l=t.split("/");for(l[0]||l.shift();r=l.shift();)"*"===(e=r[0])?(s.push("wild"),c+="/(.*)"):":"===e?(o=r.indexOf("?",1),i=r.indexOf(".",1),s.push(r.substring(1,~o?o:~i?i:r.length)),c+=~o&&!~i?"(?:/([^/]+?))?":"/([^/]+?)",~i&&(c+=(~o?"?":"")+"\\"+r.substring(i))):c+="/"+r;return{keys:s,pattern:new RegExp("^"+c+(n?"(?=$|/)":"/?$"),"i")}}(t);this.path=t,"object"==typeof n&&!0===n._sveltesparouter?(this.component=n.component,this.conditions=n.conditions||[],this.userData=n.userData,this.props=n.props||{}):(this.component=()=>Promise.resolve(n),this.conditions=[],this.props={}),this._pattern=e,this._keys=o}match(t){if(r)if("string"==typeof r&&t.startsWith(r))t=t.substr(r.length)||"/";else if(r instanceof RegExp){const n=t.match(r);n&&n[0]&&(t=t.substr(n[0].length)||"/")}const n=this._pattern.exec(t);if(null===n)return null;if(!1===this._keys)return n;const e={};let o=0;for(;o<this._keys.length;){try{e[this._keys[o]]=decodeURIComponent(n[o+1]||"")||null}catch(t){e[this._keys[o]]=null}o++}return e}async checkConditions(t){for(let n=0;n<this.conditions.length;n++)if(!await this.conditions[n](t))return!1;return!0}}const c=[];o instanceof Map?o.forEach(((t,n)=>{c.push(new s(n,t))})):Object.keys(o).forEach((t=>{c.push(new s(t,o[t]))}));let l=null,a=null,u={};const d=k();async function p(t,n){await A(),d(t,n)}let f=null;var m;i&&(window.addEventListener("popstate",(t=>{f=t.state&&t.state.scrollY?t.state:null})),m=()=>{f?window.scrollTo(f.scrollX,f.scrollY):window.scrollTo(0,0)},w().$$.after_update.push(m));let h=null,b=null;return st.subscribe((async t=>{h=t;let n=0;for(;n<c.length;){const o=c[n].match(t.location);if(!o){n++;continue}const r={route:c[n].path,location:t.location,querystring:t.querystring,userData:c[n].userData};if(!await c[n].checkConditions(r))return e(0,l=null),b=null,void p("conditionsFailed",r);p("routeLoading",Object.assign({},r));const i=c[n].component;if(b!=i){i.loading?(e(0,l=i.loading),b=i,e(1,a=i.loadingParams),e(2,u={}),p("routeLoaded",Object.assign({},r,{component:l,name:l.name}))):(e(0,l=null),b=null);const n=await i();if(t!=h)return;e(0,l=n&&n.default||n),b=i}return o&&"object"==typeof o&&Object.keys(o).length?e(1,a=o):e(1,a=null),e(2,u=c[n].props),void p("routeLoaded",Object.assign({},r,{component:l,name:l.name}))}e(0,l=null),b=null})),t.$$set=t=>{"routes"in t&&e(3,o=t.routes),"prefix"in t&&e(4,r=t.prefix),"restoreScrollState"in t&&e(5,i=t.restoreScrollState)},t.$$.update=()=>{32&t.$$.dirty&&(history.scrollRestoration=i?"manual":"auto")},[l,a,u,o,r,i,function(n){E(t,n)},function(n){E(t,n)}]}class ut extends Q{constructor(t){super(),K(this,t,at,rt,s,{routes:3,prefix:4,restoreScrollState:5})}}function dt(t,n,e){const o=t.slice();return o[4]=n[e],o}function pt(t){let n,e,o,r,i=t[4].name+"";function s(){return t[2](t[4])}return{c(){n=d("button"),e=p(i),b(n,"type","button"),b(n,"class","list-group-item list-group-item-action")},m(t,i){l(t,n,i),c(n,e),o||(r=h(n,"click",s),o=!0)},p(n,e){t=n},d(t){t&&a(n),o=!1,r()}}}function ft(n){let e,o,i,s,p,m,g,y,v,$,x,w,C,k,E,j,_,T,L,S,O,M,A,N,H,R,I,P,q=n[0],F=[];for(let t=0;t<q.length;t+=1)F[t]=pt(dt(n,q,t));return{c(){e=d("div"),o=d("div"),i=d("div"),i.innerHTML='Proposta del momento\n\t\t\t<div class="text-muted text-small">23 Settembre 2021 13:52</div>',s=f(),p=d("div"),m=d("p"),m.innerHTML='<span class="fw-bolder">mar</span> propone:',g=f(),y=d("p"),y.innerHTML='<i class="bi bi-megaphone"></i> \n\t\t\t\t<span class="fst-italic">&quot;Cantiamo titolo_canto&quot;</span>',v=f(),$=d("div"),x=d("button"),x.textContent="Partecipa",w=f(),C=d("div"),k=d("label"),k.textContent="Elenco Raccolte",E=f(),j=d("div");for(let t=0;t<F.length;t+=1)F[t].c();_=f(),T=d("div"),T.textContent="Seleziona una Raccolta.",L=f(),S=d("div"),S.innerHTML='<div class="d-grid gap-2"><button class="btn btn-primary" type="button" id="collection-btn-create">Crea una Nuova Raccolta</button></div>',O=f(),M=d("div"),A=d("div"),N=d("button"),N.textContent="Cronologia",H=f(),R=d("button"),R.textContent="Esci",b(i,"class","card-header"),b(m,"class","card-title"),b(y,"class","card-text text-center fs-5"),b(x,"class","btn btn-primary"),b(x,"type","button"),b(x,"id","commty-btn-participate"),b($,"class","d-grid gap-2"),b(p,"class","card-body"),b(o,"class","card"),b(e,"class","mb-3"),b(k,"for","colls-list"),b(k,"class","form-label"),b(j,"class","list-group"),b(j,"aria-describedby","colls-list-help"),b(j,"id","colls-list"),b(T,"class","form-text"),b(T,"id","colls-list-help"),b(C,"class","mb-3"),b(S,"class","mb-3"),b(N,"type","button"),b(N,"class","btn btn-outline-primary"),b(N,"id","commty-btn-history"),b(R,"type","button"),b(R,"class","btn btn-outline-primary"),b(A,"class","btn-group"),b(A,"role","group"),b(M,"class","mb-3")},m(t,r){l(t,e,r),c(e,o),c(o,i),c(o,s),c(o,p),c(p,m),c(p,g),c(p,y),c(p,v),c(p,$),c($,x),l(t,w,r),l(t,C,r),c(C,k),c(C,E),c(C,j);for(let t=0;t<F.length;t+=1)F[t].m(j,null);c(C,_),c(C,T),l(t,L,r),l(t,S,r),l(t,O,r),l(t,M,r),c(M,A),c(A,N),c(A,H),c(A,R),I||(P=[h(x,"click",n[1]),h(R,"click",n[3])],I=!0)},p(t,[n]){if(1&n){let e;for(q=t[0],e=0;e<q.length;e+=1){const o=dt(t,q,e);F[e]?F[e].p(o,n):(F[e]=pt(o),F[e].c(),F[e].m(j,null))}for(;e<F.length;e+=1)F[e].d(1);F.length=q.length}},i:t,o:t,d(t){t&&a(e),t&&a(w),t&&a(C),u(F,t),t&&a(L),t&&a(S),t&&a(O),t&&a(M),I=!1,r(P)}}}function mt(t){return[[{id:"1",name:"Cantici",type:"c"},{id:"2",name:"Letture",type:"l"},{id:"3",name:"Battute",type:"l"}],()=>ct("/content"),t=>ct("/collection/"+t.id),()=>lt()]}var ht="https://antoniof-ipp3ffev08i-apigcp.nimbella.io/api/commty-service/getCommunities",bt="https://antoniof-ipp3ffev08i-apigcp.nimbella.io/api/commty-service/getContents",gt="https://antoniof-ipp3ffev08i-apigcp.nimbella.io/api/commty-service/signin";function yt(t,n,e){const o=t.slice();return o[8]=n[e],o}function vt(t){let n,e,o,r,i=t[8].name+"";function s(){return t[3](t[8])}return{c(){n=d("button"),e=p(i),b(n,"type","button"),b(n,"class","list-group-item list-group-item-action"),v(n,"active",t[1].communityId===t[8].id)},m(t,i){l(t,n,i),c(n,e),o||(r=h(n,"click",s),o=!0)},p(o,r){t=o,1&r&&i!==(i=t[8].name+"")&&g(e,i),3&r&&v(n,"active",t[1].communityId===t[8].id)},d(t){t&&a(n),o=!1,r()}}}function $t(n){let e,o,i,s,p,m,g,v,$,x,w,C,k,E,j,_,T,L,S,O,M,A,N,H,R,I,P,q,F=n[0],z=[];for(let t=0;t<F.length;t+=1)z[t]=vt(yt(n,F,t));return{c(){e=d("div"),e.innerHTML="<h5>Accesso</h5>",o=f(),i=d("div"),s=d("label"),s.textContent="Elenco delle Community",p=f(),m=d("div");for(let t=0;t<z.length;t+=1)z[t].c();g=f(),v=d("div"),v.textContent="Seleziona la Community in cui vuoi entrare.",$=f(),x=d("div"),w=d("input"),C=f(),k=d("div"),k.textContent="Serve per entrare nella community selezionata.",E=f(),j=d("hr"),_=f(),T=d("div"),L=d("label"),L.textContent="La tua Identità nella Community",S=f(),O=d("input"),M=f(),A=d("div"),A.innerHTML="Prime 2 lettere del tuo nome + prima lettera del tuo cognome.\n    <br/>\n    Es.: Mario Rossi = mar.",N=f(),H=d("div"),R=d("div"),I=d("button"),I.textContent="Entra",b(e,"class","mb-3"),b(s,"for","commty-list"),b(s,"class","form-label"),b(m,"class","list-group"),b(m,"aria-describedby","commty-list-help"),b(m,"id","commty-list"),b(v,"class","form-text"),b(v,"id","commty-list-help"),b(i,"class","mb-3"),b(w,"type","text"),b(w,"class","form-control"),b(w,"placeholder","Parola d'ordine della Community"),b(w,"aria-describedby","watchword-help"),b(w,"id","watchword"),b(k,"class","form-text"),b(k,"id","watchword-help"),b(x,"class","mb-3"),b(L,"for","commty-username"),b(L,"class","form-label"),b(O,"type","text"),b(O,"class","form-control"),b(O,"maxlength","3"),b(O,"placeholder","Nome Utente"),b(O,"aria-describedby","commty-username-help"),b(O,"id","commty-username"),b(A,"class","form-text"),b(A,"id","commty-username-help"),b(T,"class","mb-3"),b(I,"class","btn btn-primary"),b(I,"type","button"),b(I,"id","commty-btn-enter"),b(R,"class","d-grid gap-2"),b(H,"class","mb-3")},m(t,r){l(t,e,r),l(t,o,r),l(t,i,r),c(i,s),c(i,p),c(i,m);for(let t=0;t<z.length;t+=1)z[t].m(m,null);c(i,g),c(i,v),l(t,$,r),l(t,x,r),c(x,w),y(w,n[1].watchword),c(x,C),c(x,k),l(t,E,r),l(t,j,r),l(t,_,r),l(t,T,r),c(T,L),c(T,S),c(T,O),y(O,n[1].username),c(T,M),c(T,A),l(t,N,r),l(t,H,r),c(H,R),c(R,I),P||(q=[h(w,"input",n[4]),h(O,"input",n[5]),h(I,"click",n[6])],P=!0)},p(t,[n]){if(3&n){let e;for(F=t[0],e=0;e<F.length;e+=1){const o=yt(t,F,e);z[e]?z[e].p(o,n):(z[e]=vt(o),z[e].c(),z[e].m(m,null))}for(;e<z.length;e+=1)z[e].d(1);z.length=F.length}2&n&&w.value!==t[1].watchword&&y(w,t[1].watchword),2&n&&O.value!==t[1].username&&y(O,t[1].username)},i:t,o:t,d(t){t&&a(e),t&&a(o),t&&a(i),u(z,t),t&&a($),t&&a(x),t&&a(E),t&&a(j),t&&a(_),t&&a(T),t&&a(N),t&&a(H),P=!1,r(q)}}}function xt(t,n,e){let o=[],r={};function i(){fetch(gt,{method:"post",headers:{"Content-Type":"application/json"},body:JSON.stringify(r)}).then((t=>t.json())).then((t=>{console.log("data: "+JSON.stringify(t)),"1"==t.status.code?ct("/home"):(alert("errore"),console.log("errore"))}))}C((()=>{fetch(ht).then((t=>t.json())).then((t=>{e(0,o=t),e(1,r.communityId=o[0].id,r)}))}));return[o,r,i,t=>e(1,r.communityId=t.id,r),function(){r.watchword=this.value,e(1,r)},function(){r.username=this.value,e(1,r)},()=>i()]}function wt(t,n,e){const o=t.slice();return o[6]=n[e],o}function Ct(t){let n,e,o,r,i=t[6].name+"";return{c(){n=d("button"),e=p(i),b(n,"type","button"),b(n,"class","list-group-item list-group-item-action")},m(i,s){l(i,n,s),c(n,e),o||(r=h(n,"click",t[3]),o=!0)},p(t,n){1&n&&i!==(i=t[6].name+"")&&g(e,i)},d(t){t&&a(n),o=!1,r()}}}function kt(n){let e,o,i,s,p,m,g,y,v,$,x,w,C,k=n[0],E=[];for(let t=0;t<k.length;t+=1)E[t]=Ct(wt(n,k,t));return{c(){e=d("div"),o=d("button"),o.textContent="indietro",i=f(),s=d("div"),p=d("label"),m=f(),g=d("div");for(let t=0;t<E.length;t+=1)E[t].c();y=f(),v=d("div"),$=d("div"),x=d("button"),x.textContent="Aggiungi Nuovo",b(o,"type","button"),b(o,"class","btn btn-outline-primary"),b(e,"class","mb-3 text-center"),b(p,"for","content-list"),b(p,"class","form-label"),b(p,"id","content-list-label"),b(g,"class","list-group"),b(g,"id","content-list"),b(s,"class","mb-3"),b(x,"class","btn btn-primary"),b(x,"type","button"),b(x,"id","content-btn-create"),b($,"class","d-grid gap-2"),b(v,"class","mb-3")},m(t,r){l(t,e,r),c(e,o),l(t,i,r),l(t,s,r),c(s,p),c(s,m),c(s,g);for(let t=0;t<E.length;t+=1)E[t].m(g,null);l(t,y,r),l(t,v,r),c(v,$),c($,x),w||(C=[h(o,"click",n[2]),h(x,"click",n[4])],w=!0)},p(t,[n]){if(1&n){let e;for(k=t[0],e=0;e<k.length;e+=1){const o=wt(t,k,e);E[e]?E[e].p(o,n):(E[e]=Ct(o),E[e].c(),E[e].m(g,null))}for(;e<E.length;e+=1)E[e].d(1);E.length=k.length}},i:t,o:t,d(t){t&&a(e),t&&a(i),t&&a(s),u(E,t),t&&a(y),t&&a(v),w=!1,r(C)}}}function Et(t,n,e){let{params:o={}}=n,r=[];C((()=>{return t=o.idCollection,void fetch(bt+"?idCollection="+t).then((t=>t.json())).then((t=>{e(0,r=t)}));var t}));return t.$$set=t=>{"params"in t&&e(1,o=t.params)},[r,o,()=>lt(),()=>ct("/content"),()=>ct("/content-new")]}function jt(n){let e,o,r,i,s,u,p,m,g,y;return{c(){e=d("div"),o=d("button"),o.textContent="indietro",r=f(),i=d("div"),i.textContent="Titolo",s=f(),u=d("div"),u.innerHTML="Testo<br/>\n    Testo<br/>\n    Testo<br/>\n    Testo<br/>\n    Testo<br/>\n    Testo<br/>\n    Testo<br/>",p=f(),m=d("div"),m.innerHTML='<div class="d-grid gap-2"><button class="btn btn-primary" type="button"><i class="bi bi-megaphone"></i> Proponi</button></div>',b(o,"type","button"),b(o,"class","btn btn-outline-primary"),b(e,"class","mb-3 text-center"),b(i,"class","mb-3"),b(u,"class","mb-3"),b(m,"class","mb-3")},m(t,a){l(t,e,a),c(e,o),l(t,r,a),l(t,i,a),l(t,s,a),l(t,u,a),l(t,p,a),l(t,m,a),g||(y=h(o,"click",n[0]),g=!0)},p:t,i:t,o:t,d(t){t&&a(e),t&&a(r),t&&a(i),t&&a(s),t&&a(u),t&&a(p),t&&a(m),g=!1,y()}}}function _t(t){return[()=>lt()]}function Tt(n){let e,o,r,i,s,u,p,m;return{c(){e=d("div"),o=d("button"),o.textContent="indietro",r=f(),i=d("div"),i.innerHTML='<label for="load-photo" class="load-photo-label">Caricamento Foto</label> \n    <input class="form-control" type="file" aria-describedby="load-photo-help" id="load-photo"/> \n    <div class="form-text" id="load-photo-help">Scegli una Foto del testo che vuoi caricare.</div>',s=f(),u=d("div"),u.innerHTML='<div class="d-grid gap-2"><button class="btn btn-primary" type="button">Carica</button></div>',b(o,"type","button"),b(o,"class","btn btn-outline-primary"),b(e,"class","mb-3 text-center"),b(i,"class","mb-3"),b(u,"class","mb-3")},m(t,a){l(t,e,a),c(e,o),l(t,r,a),l(t,i,a),l(t,s,a),l(t,u,a),p||(m=h(o,"click",n[0]),p=!0)},p:t,i:t,o:t,d(t){t&&a(e),t&&a(r),t&&a(i),t&&a(s),t&&a(u),p=!1,m()}}}function Lt(t){return[()=>lt()]}function St(n){let e,o,r;return{c(){e=d("h1"),e.textContent="Not Found",o=f(),r=d("p"),r.textContent="This route doesn't exist.",b(e,"class","svelte-1vvja26")},m(t,n){l(t,e,n),l(t,o,n),l(t,r,n)},p:t,i:t,o:t,d(t){t&&a(e),t&&a(o),t&&a(r)}}}var Ot={"/":class extends Q{constructor(t){super(),K(this,t,xt,$t,s,{})}},"/home":class extends Q{constructor(t){super(),K(this,t,mt,ft,s,{})}},"/collection/:idCollection":class extends Q{constructor(t){super(),K(this,t,Et,kt,s,{params:1})}},"/content":class extends Q{constructor(t){super(),K(this,t,_t,jt,s,{})}},"/content-new":class extends Q{constructor(t){super(),K(this,t,Lt,Tt,s,{})}},"*":class extends Q{constructor(t){super(),K(this,t,null,St,s,{})}}};function Mt(n){let e,o,r,i,s,u,p,m,h,g,y,v,$;return v=new ut({props:{routes:Ot}}),{c(){e=d("link"),o=d("link"),r=d("script"),s=d("script"),p=f(),m=d("main"),h=d("div"),g=d("div"),g.innerHTML="<h1>CommunityApp</h1>",y=f(),U(v.$$.fragment),b(e,"href","https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"),b(e,"rel","stylesheet"),b(o,"rel","stylesheet"),b(o,"href","https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"),r.src!==(i="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js")&&b(r,"src","https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"),s.src!==(u="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js")&&b(s,"src","https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"),b(g,"class","mb-3"),b(h,"class","container")},m(t,n){c(document.head,e),c(document.head,o),c(document.head,r),c(document.head,s),l(t,p,n),l(t,m,n),c(m,h),c(h,g),c(h,y),W(v,h,null),$=!0},p:t,i(t){$||(Y(v.$$.fragment,t),$=!0)},o(t){X(v.$$.fragment,t),$=!1},d(t){a(e),a(o),a(r),a(s),t&&a(p),t&&a(m),G(v)}}}return new class extends Q{constructor(t){super(),K(this,t,null,Mt,s,{})}}({target:document.body})}();
//# sourceMappingURL=bundle.js.map