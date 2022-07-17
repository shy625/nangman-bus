# pandas 연습

## RNA-seq : 염기서열분석

attribute of table : Geneid(유전자 ID), P1 ~ P10, C1 ~ C10

1. 0 value는 1로 바꿔라 에러나니까 ⇒ 하지만 0이없음

## find 5 candidates of index genes and normalization

**인덱스 유전자 및 정규화의 5개 후보 찾기**

every 15951 gene will be divided by the remaining 15950 genes

**모든 15951개의 유전자는 자신을 제외한 15950개의 유전자로 나눠 봐야함**

sort based on low NVAR(normalized standard deviation of control samples)

**낮은 NVAR(대조 표본의 정규화된 표준 편차)을 기준으로 정렬**

⇒ STDEV(C1n : C10n) / AVERAGE(C1n : C10n)

⇒takes five normalized data (such as ZNF750/DNAJC22) for each dataset of 15951 * 5

**15951 * 5의 각 데이터 세트에 대해 5개의 정규화된 데이터(예: ZNF750/DNAJC22)를 사용합니다.**

⇒ write a CSV file (norm_results.CSV) with dataset of 15951 * 5

**15951 * 5의 데이터 세트로 CSV 파일(norm_results.CSV) 작성**

with 23 columns : 

- GeneID : A/B

- Ratio : Median(P1n:P10n)/Median(C1n:C10n)

- NVAR : STDEV(C1n:C10n)/AVERAGE(C1n:C10n)

- P1n to P10n : p1 of A / p1 of B ~ p10 of A / p10 of B

- C1n to C10n :  c1 of A / c1 of B ~ c10 of A / c10 of B

![1.png](1.png)

STDEV : 표준편차

![2.png](2.png)

MEDIAN : 중간값, (짝수는 중간 두개 숫자의 평균)

![3.png](3.png)
