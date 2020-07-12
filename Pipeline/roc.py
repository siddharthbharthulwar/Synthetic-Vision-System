import matplotlib.pyplot as plt
import numpy as np
from scipy import interp
from sklearn.datasets import make_classification
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.model_selection import cross_val_predict
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import Pipeline
from sklearn.model_selection import KFold
from sklearn.metrics import roc_curve, auc
from itertools import cycle
from scipy import interp
from sklearn.preprocessing import label_binarize
from sklearn.multiclass import OneVsRestClassifier
#plt.style.use('ggplot')


X, y = make_classification(n_samples=2000, random_state=100, flip_y=0.09)

kf = KFold(n_splits = 5, shuffle = True, random_state= 0)
clf = LinearDiscriminantAnalysis()
pipe= Pipeline([('scaler', StandardScaler()), ('clf', clf)])

tprs = []
aucs = []
base_fpr = np.linspace(0, 1, 101)
colors = ['darksalmon', 'gold', 'royalblue', 'mediumseagreen', 'violet']
plt.figure(figsize=(12, 8))
for i, (train, test) in enumerate(kf.split(X,y)):
    model = pipe.fit(X[train], y[train])
    y_score = model.predict_proba(X[test])
    fpr, tpr, _ = roc_curve(y[test], y_score[:, 1])
    roc_auc = auc(fpr, tpr)
   # roc_auc += 0.09
    aucs.append(roc_auc)
    plt.plot(fpr, tpr, lw=1, alpha=0.6, label='ROC fold %d (AUC = %0.2f)' % (i, roc_auc), c = colors[i])
    tpr = interp(base_fpr, fpr, tpr)
    tpr[0] = 0.0
    tprs.append(tpr)

tprs = np.array(tprs)
mean_tprs = tprs.mean(axis=0)
std = tprs.std(axis=0)

mean_auc = auc(base_fpr, mean_tprs)
std_auc = np.std(aucs)

tprs_upper = np.minimum(mean_tprs + std, 1)
tprs_lower = mean_tprs - std

plt.plot(base_fpr, mean_tprs, 'b', alpha = 0.8, label=r'Mean ROC (AUC = %0.2f $\pm$ %0.2f)' % (mean_auc, std_auc),)
plt.fill_between(base_fpr, tprs_lower, tprs_upper, color = 'blue', alpha = 0.2)
plt.plot([0, 1], [0, 1], linestyle = '--', lw = 2, color = 'r', label = 'Luck', alpha= 0.8)
plt.xlim([-0.01, 1.01])
plt.ylim([-0.01, 1.01])
plt.ylabel('True Positive Rate')
plt.xlabel('False Positive Rate')
plt.legend(loc="lower right")
plt.title('Receiver operating characteristic (ROC) curve')
#plt.axes().set_aspect('equal', 'datalim')
plt.show()