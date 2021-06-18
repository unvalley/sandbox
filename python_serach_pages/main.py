import csv
import glob
from collections import defaultdict


# return: { 'task_id' : [...search_pages], ...}
def load_search_pagegs_by_task_id(path: str):
    search_pages = defaultdict(list)
    with open(path, "r") as f:
        for l in csv.DictReader(f):
            task_id = l['task_id']
            search_pages[task_id].append(l)
    return search_pages


def split_list(lst, n):
    for i in range(0, len(lst), n):
        yield lst[i:i + n]


def ranking(search_pages):
    result = []
    score_order_list = sorted(
        search_pages, reverse=True, key=lambda x: x['score'])

    MAX_LENGTH = 10
    splitted_score_order_list = list(split_list(score_order_list, MAX_LENGTH))

    for i in range(MAX_LENGTH):
        for splitted in zip(splitted_score_order_list):
            try:
                result.append(list(splitted[0][i].values()))
                result.append(list(splitted[1][i].values()))
                result.append(list(splitted[2][i].values()))
            except IndexError:
                pass

    return result


def change_rank(search_pages_keyby_task_id):
    new_ranked_search_pages_keyby_task_id = defaultdict(list)

    for k, search_pages in search_pages_keyby_task_id.items():
        ranked_search_pages = ranking(search_pages)
        new_ranked_search_pages_keyby_task_id[k].append(ranked_search_pages)

    return new_ranked_search_pages_keyby_task_id


def write_csv(responses: list, path: str):
    HEADER = ['id', 'title', 'url', 'snippet', 'score',
              'item_id', 'task_id', 'hospital_expertise', 'displayOrder']
    with open(path, "w") as f:
        writer = csv.writer(f, quoting=csv.QUOTE_NONNUMERIC)
        writer.writerow(HEADER)
        for res in responses:
            r = [[*v, idx+1] for idx, v in enumerate(res)]
            writer.writerows(r)


def main():
    paths = glob.glob('from/*.csv')

    for path in paths:
        search_pages_keyby_task_id = load_search_pagegs_by_task_id(path)

        ranked_search_pagegs = change_rank(search_pages_keyby_task_id)
        for k, v in ranked_search_pagegs.items():
            write_path = f"to/{k}.csv"
            write_csv(v, write_path)


if __name__ == '__main__':
    main()
