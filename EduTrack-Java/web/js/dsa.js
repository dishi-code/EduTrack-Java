/* ---------- DSA LIBRARY ---------- */
const kmpSearch=(t,p)=>{t=t.toLowerCase();p=p.toLowerCase();if(!p)return[];const l=[0];for(let i=1,k=0;i<p.length;){if(p[i]===p[k])l[i++]=++k;else if(k)k=l[k-1];else l[i++]=0}
const r=[];for(let i=0,j=0;i<t.length;){if(t[i]===p[j]){i++;j++;if(j===p.length){r.push(i-j);j=l[j-1]}}else if(j)j=l[j-1];else i++}return r};
const rabinKarp=(t,p)=>{t=t.toLowerCase();p=p.toLowerCase();const n=t.length,m=p.length,B=256,M=1000003;if(!m||m>n)return[];
let h=1,hp=0,ht=0,r=[];for(let i=0;i<m-1;i++)h=h*B%M;for(let i=0;i<m;i++){hp=(hp*B+p.charCodeAt(i))%M;ht=(ht*B+t.charCodeAt(i))%M}
for(let i=0;i<=n-m;i++){if(hp===ht&&t.substr(i,m)===p)r.push(i);if(i<n-m)ht=((ht-t.charCodeAt(i)*h)*B+t.charCodeAt(i+m))%M,ht=(ht+M)%M}return r};
const editDistance=(a,b)=>{const d=Array.from({length:a.length+1},(_,i)=>[i,...Array(b.length).fill(0)]);for(let j=1;j<=b.length;j++)d[0][j]=j;
for(let i=1;i<=a.length;i++)for(let j=1;j<=b.length;j++)d[i][j]=Math.min(d[i-1][j]+1,d[i][j-1]+1,d[i-1][j-1]+(a[i-1]===b[j-1]?0:1));return d[a.length][b.length]};
const suffixArray=s=>[...Array(s.length).keys()].sort((a,b)=>s.slice(a)<s.slice(b)?-1:1);
const lcpArray=(s,sa)=>{const n=s.length,rk=[],lcp=Array(n).fill(0);sa.forEach((v,i)=>rk[v]=i);for(let i=0,h=0;i<n;i++){if(rk[i]>0){const j=sa[rk[i]-1];while(s[i+h]===s[j+h])h++;lcp[rk[i]]=h;if(h>0)h--}else h=0}return lcp};
const longestCommon=(a,b)=>{a=a.toLowerCase();b=b.toLowerCase();const s=a+'\u0001'+b,sa=suffixArray(s),lcp=lcpArray(s,sa);let best=0,at=0;
for(let i=1;i<sa.length;i++){const x=sa[i]<a.length,y=sa[i-1]<a.length;if(x!==y&&lcp[i]>best){best=lcp[i];at=sa[i]}}return{len:best,text:s.substr(at,best)}};
const maxFlow=(cap,s,t)=>{const n=cap.length;let flow=0;while(true){const par=Array(n).fill(-1);par[s]=s;const q=[s];while(q.length&&par[t]<0){const u=q.shift();for(let v=0;v<n;v++)if(par[v]<0&&cap[u][v]>0){par[v]=u;q.push(v)}}
if(par[t]<0)return flow;let f=1e9;for(let v=t;v!==s;v=par[v])f=Math.min(f,cap[par[v]][v]);for(let v=t;v!==s;v=par[v]){cap[par[v]][v]-=f;cap[v][par[v]]+=f}flow+=f}};
const rqs=a=>{if(a.length<2)return a;const p=a[Math.floor(Math.random()*a.length)];return[...rqs(a.filter(x=>x>p)),...a.filter(x=>x===p),...rqs(a.filter(x=>x<p))]};
const reservoir=(a,k)=>{const r=a.slice(0,k);for(let i=k;i<a.length;i++){const j=Math.floor(Math.random()*(i+1));if(j<k)r[j]=a[i]}return r};

